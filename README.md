# Getting Started
This is the implementation for managing the relationship of users with the teams with the appropriate role.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.11/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.11/gradle-plugin/reference/html/#build-image)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

### Scenario
The task is to establish the relation between user and the team and also link the appropriate role with the user.

### Approach
It is obvious that to assign the role to the member , one need to add the relationship between these two entities.
Since one member can have multiple roles , so there is a need to add one-to many relationship.

Also, to assign the membership where the user with the appropriate role is assigned to specific team, we should have a composite key to identify the membership uniquely

The solution is based on hexagonal pattern since it keep the domain logic unchanged and open the ports for exposing domain to different other sources.

### Instructions to run 

#### Pre-requisites
Install [Docker Desktop](https://docs.docker.com/desktop/install/linux-install/)
Install JDK11

Run command: ``` docker-compose up ```  or  for force building image ``` docker-compose --pull always up ```

### Swagger Docs
After launching the app from the above command. Access the url: http://localhost:8081/swagger-ui/index.html

### API Contract

1. Create roles /roles

   Request Method : POST
   Body {"roleType": "Developer", "isDefault": true}
   
   Response : 
   ```
   {
   "id": 1,
   "roleType": "Developer",
   "isDefault": true
   }
   ```

2. Create Membership /teams/<team-id>/users/<user-id>
   Request Method : POST
   Body {"roleId": 1}

   Response :
   ```
   {
   "userId": "7ca5f476-4beb-4aae-9a50-2ac5c78be9e9",
   "teamId": "1",
   "roleDto": {
   "id": 1,
   "roleType": "Developer",
   "default": true
   }
   }
   ```
3. Lookup Membership /teams/<teamid>/users/<user-id>

    Response :
    ```
    {
    "id": 1,
    "roleType": "Developer",
    "default": true
    }
      ```

### Improvements

#### Existing Users/Teams  API
Pagination, Sorting and versioning can be implemented in both the APIs.

  Users API
1. Can expose POST request to create member.
2. Can also show multiple roles assigned to the given user
3. Can also allow displaying the team information to which the user belongs

Teams API
1. Can expose POST request to create team.
2. Allow adding relation with the user entity.
3. Can display all users for the given team.


Can add one more API resource for roles management.


#### For Suggested approach
1. Introduce pagination and versioning.
2. Add more test cases to increase the coverage
3. Add CRUD for teams resource, users resource and  roles resource with all the attributes
4. Introduce caching for get operations and re-validate cache regularly
5. Using ``` ./gradlew bootBuildImage``` to build the image using spring buildpacks and avoid using Dockerfile.




