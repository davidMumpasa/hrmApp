package com.xgileit.hrm.service;

import com.xgileit.hrm.dto.request.user.UserRequest;
import com.xgileit.hrm.dto.response.user.*;
import com.xgileit.hrm.exception.AlreadyInUsed;
import com.xgileit.hrm.exception.BadRequest;
import com.xgileit.hrm.exception.NotFound;
import com.xgileit.hrm.persistance.entity.*;
import com.xgileit.hrm.persistance.repository.LoginRepository;
import com.xgileit.hrm.persistance.repository.RoleUserRepository;
import com.xgileit.hrm.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RoleUserRepository roleUserRepository;
    private final LoginRepository loginRepository;
    private final UserRepository userRepository;

    public UserResponse updateUser(int userId, UserRequest userRequest) throws BadRequest, AlreadyInUsed, NotFound {

        // Validating for Null Request
        if(isNull(userRequest)){
            throw new BadRequest("Request can not be null");
        }

        // Ensuring that username is unique with respect to other users
        verifyUsername(userId, userRequest);

        return null;
    }

    /**
     * Get The list of users in the DB, filter and return a list of filtered Users
     */
    public Page<User> filterByRoleAndStatus(List<Integer> arrayRoleId, List<Integer> statusId, Pageable pageable) throws NotFound {
        // find All user mappings from the DB
        List<User> users = userRepository.findAll();
        List<User> filteredUsers = new ArrayList<>();

        if (users.isEmpty()) {
            throw new NotFound("No record available for provided filter key ");
        } else {


            // loop through the user's list
            for (User user : users) {

                List<RoleUserMapping> roleUserMappings = user.getRoleUserMappings();

                // extract the role and status id from the list
                for (int i = 0; i < arrayRoleId.size(); i++) {
                    int role = arrayRoleId.get(i);
                    int status = statusId.get(i);

                    // filter the list by role and user's Status id
                    List<RoleUserMapping> filteredRolesStatus = roleUserMappings.stream()
                            .filter(w -> w.getRole().getRoleId() == role || user.getStatus().getStatusId() == status)
                            .collect(Collectors.toList());

                    // loop through the RoleUserMapping list and get the filtered user
                    for(RoleUserMapping roleUserMapping:filteredRolesStatus){
                        User user1 = roleUserMapping.getUser();

                        // Add the user to the user's list
                        filteredUsers.add(user1);
                    }
                }
            }
        }

        // convert the user filtered list to the Page user and return it
        Page<User> pageUser = new PageImpl<User>(filteredUsers);

        return pageUser;
    }

    /**
     * Filter, sort ,set Response, and UserResponsePaginationDTO
     */
    public UserResponsePaginationDTO filterUser(List<Integer> roleId, List<Integer> statusId, int pageLength, int pageNumber) throws NotFound {
        Pageable pageable = PageRequest.of(pageNumber, pageLength);

        UserResponsePaginationDTO userResponsePaginationDTO = new UserResponsePaginationDTO();

        // call the filterByRoleAndStatus method and return a page user list
        Page<User> pageUsers = filterByRoleAndStatus(roleId, statusId, pageable);

        if (pageUsers.isEmpty()) {
            throw new NotFound("No record available for provided filter key ");
        } else {
            // get the user's list from the page user
            List<User> users = pageUsers.toList();

            // parse the users list to the sort methode
            List<User> sortedFilteredUsers = sortByModifiedDate(users);

            // parse the sortedFilteredUsers to the user response method
            List<UserResponse> responses = userResponse(sortedFilteredUsers);

            // set the page number, size,TotalElements,TotalPages and response to the object
            userResponsePaginationDTO.setPageNo(pageable.getPageNumber());
            userResponsePaginationDTO.setPageSize(pageable.getPageSize());
            userResponsePaginationDTO.setTotalElements(pageUsers.getTotalElements());
            userResponsePaginationDTO.setTotalPages(pageUsers.getTotalPages());
            userResponsePaginationDTO.setUserResponse(responses);
            userResponsePaginationDTO.setLast(pageUsers.isLast());
        }

        return userResponsePaginationDTO;
    }


    /**
     * Set the User's fields to the Response
     */

    public List<UserResponse> userResponse(List<User> users) {
        // response List to be returned by the Methode
        List<UserResponse> responses = new ArrayList<>();

        for (User user : users) {
            // create a response status object
            ResponseStatusDTO responseStatus = new ResponseStatusDTO();
            // set the status id and name to the object
            responseStatus.setStatusId(user.getStatus().getStatusId());
            responseStatus.setStatusName(user.getStatus().getStatusName());

            // create a Response object
            UserResponse response = new UserResponse();

            // create a ResponseUserType object
            ResponseUserTypeDTO responseUserType = new ResponseUserTypeDTO();
            responseUserType.setUsertypeId(user.getUserType().getUserTypeId());
            responseUserType.setUserTypeName(user.getUserType().getUserTypeName());

            // set the user's field to the response
            response.setUserId(user.getUserId());
            response.setFirstName(user.getFirstName());
            response.setUserName(user.getUsername());
            response.setLastName(user.getLastName());
            response.setEmail(user.getPrimaryEmail());
            response.setDomain(user.getDomain());
            response.setPhoneNumber(user.getPhoneNumber());
            response.setStatus(responseStatus);
            response.setUserType(responseUserType);

            // create a role Array to be set to the response Object
            List<ResponseRoleDTO> roles = new ArrayList<>();

            // get the RoleUserMapping from the user
            List<RoleUserMapping> roleUserMappings = user.getRoleUserMappings();

            // create a response Role object
            ResponseRoleDTO responseRole = new ResponseRoleDTO();

            for (RoleUserMapping roleUserMapping : roleUserMappings) {
                responseRole.setRoleId(roleUserMapping.getRole().getRoleId());
                responseRole.setRoleName(roleUserMapping.getRole().getRoleName());
                roles.add(responseRole);
            }

            // create a country Array to be set to the response Object
            List<ResponseCountryDTO> countries = new ArrayList<>();
            // set the role Array to the response
            response.setRoles(roles);

            // create a response Country
            ResponseCountryDTO responseCountry = new ResponseCountryDTO();

            // get the Country UserMapping array from the User, which contains the user's country and add it to the countries Array
            for (CountryUserMapping countryUserMapping : user.getCountryUserMappings()) {
                responseCountry.setCountryId(countryUserMapping.getCountry().getCountryId());
                responseCountry.setCountryCode(countryUserMapping.getCountry().getCountryCode());
                responseCountry.setCountryName(countryUserMapping.getCountry().getCountryName());
                countries.add(responseCountry);
            }

            // set the countries Array to the response
            response.setCountryDetails(countries);

            // Add the Response object to the List
            responses.add(response);
        }

        return responses;
    }

    /**
     * Sort user by Modified Date and return a list
     */
    public List<User> sortByModifiedDate(List<User> filteredUsers) {

        // Define the sorting order based on the Date field
        Comparator<User> comparator = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getModifyDate().compareTo(o2.getModifyDate());
            }
        };

        List<User> mutableList = new ArrayList<>(filteredUsers);
        // Sort the list using the Comparator
        Collections.sort(mutableList, comparator);

        // Print the sorted list

        System.out.println("-----------------------------------------------------" + "\n");
        for (User user : filteredUsers) {
            System.out.println(user.getModifyDate());
        }
        System.out.println("-----------------------------------------------------" + "\n");

        return mutableList;

    }

    /**
     * Get single User and return him
     */
    public User getUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();

        return user;

    }

    /**
     * Verifying the new username of the user to be not existing in DB with respect to other
     * user to avoid the duplicates.
     */
    private void verifyUsername(int userId, UserRequest request) throws AlreadyInUsed {
        Optional<Login> userLoginDetails = loginRepository.findByUserName(request.getUserName());
        if((userId > 0 && (userLoginDetails.isPresent() && userLoginDetails.get().getLoginId() != userId))
                || (userId < 0 && userLoginDetails.isPresent())) {
            throw new AlreadyInUsed("Username already in use");
        }
    }

}
