package com.xgileit.hrm.service;

import com.xgileit.hrm.dto.request.user.UserRequest;
import com.xgileit.hrm.dto.response.user.UserResponse;
import com.xgileit.hrm.exception.AlreadyInUsed;
import com.xgileit.hrm.exception.BadRequest;
import com.xgileit.hrm.exception.NotFound;
import com.xgileit.hrm.persistance.entity.*;
import com.xgileit.hrm.persistance.repository.LoginRepository;
import com.xgileit.hrm.persistance.repository.RoleUserRepository;
import com.xgileit.hrm.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
    public List<RoleUserMapping> findByList(String reqCriteria) {
        // find All role user mappings from the DB as it contains the role and the user's status that we have to filter by
        List<RoleUserMapping> roleUserMappings = roleUserRepository.findAll();

        String criteria = reqCriteria.toLowerCase();

        // filter the list by role and user's Status
        List<RoleUserMapping> filteredRoleUser = roleUserMappings.stream()
                .filter(w -> w.getRole().getRoleName().toLowerCase().contains(criteria) || w.getUser().getStatus().getStatusName().toLowerCase().startsWith(criteria))
                .collect(Collectors.toList());


        return filteredRoleUser;
    }

    /**
     * Set the User's fields to the Response
     */
    public List<UserResponse> sortAndSetResponse(List<RoleUserMapping> filteredRoleUser, List<User> filteredUsers) {

        // Create a response List to be returned by the Methode
        List<UserResponse> responses = null;

        // Loop through the role userMapping to geta Specific User his role
        for (RoleUserMapping roleUserMapping : filteredRoleUser) {
            responses = new ArrayList<>();
            for (User user : filteredUsers) {

                // create a Response object
                UserResponse response = new UserResponse();

                // create a role Array to be set to the response Object
                List<Role> roles = new ArrayList<>();

                // create a country Array to be set to the response Object
                List<Country> countries = new ArrayList<>();

                // set user fields to the response
                response.setUserId(user.getUserId());
                response.setFirstName(user.getFirstName());
                response.setUserName(user.getUsername());
                response.setLastName(user.getLastName());
                response.setEmail(user.getPrimaryEmail());
                response.setDomain(user.getDomain());
                response.setPhoneNumber(user.getPhoneNumber());
                response.setStatus(user.getStatus());

                // get role from the roleUserMapping and set it the role array
                roles.add(roleUserMapping.getRole());

                // set the role Array to the response
                response.setRoles(roles);

                // get the Country UserMapping array from the User, which contains the user's country and add it to the countries Array
                for (CountryUserMapping countryUserMapping : user.getCountryUserMappings()) {
                    countries.add(countryUserMapping.getCountry());
                }

                // set the countries Array to the response
                response.setCountryDetails(countries);

                // Add the Response object to the List
                responses.add(response);

            }
        }

        return responses;
    }

    /**
     * Sort user by Modified Date and return a list
     */
    public List<User> sortByModifiedDate(List<RoleUserMapping> filteredRoleUser) {

        // user list to be extracted from the filteredRoleUser array
        List<User> filteredUsers = new ArrayList<>();

        // extract the user List from the filteredRoleUser array
        for (RoleUserMapping roleUserMapping : filteredRoleUser) {
            User user = roleUserMapping.getUser();
            filteredUsers.add(user);
        }

        // Define the sorting order based on the Date field
        Comparator<User> comparator = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getModifyDate().compareTo(o2.getModifyDate());
            }

        };

        // Sort the list using the Comparator
        Collections.sort(filteredUsers, comparator);

        // Print the sorted list

        System.out.println("-----------------------------------------------------"+"\n");
        for (User user : filteredUsers) {
            System.out.println(user.getModifyDate());
        }
        System.out.println("-----------------------------------------------------"+"\n");

        return filteredUsers;

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
