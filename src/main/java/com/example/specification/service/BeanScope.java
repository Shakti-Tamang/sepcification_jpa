package com.example.specification.service;

public class BeanScope {


//    ctrl+alt+l
//
//    Record vs DTO — Key Difference
//    java// Record — IMMUTABLE (cannot change fields after creation)
//    public record UserRecord(String name, String email) {}
//
//    // DTO class — MUTABLE (can change fields)
//    public class UserDto {
//        private String name;
//        private String email;
//        // getters + setters
//    }
//
//    Can you use Record for Update?
//    Technically yes — but it has limitations:
//    java// Update record
//    public record UpdateUserRecord(
//            String name,
//            String email,
//            String address
//    ) {}
//
//    // Usage
//    UpdateUserRecord dto = new UpdateUserRecord("John", "john@test.com", "Kathmandu");
//    Problem — all fields are required:
//    java// ❌ Cannot do partial update
//    // If you only want to update name, you still must pass all fields
//    UpdateUserRecord dto = new UpdateUserRecord("John", null, null);
//// Works but ugly — null handling becomes your problem
//
//    When Record is BETTER
//1. Response DTOs (read only)
//    java// Perfect — you just return data, never modify it
//    public record UserResponse(
//            String id,
//            String name,
//            String email,
//            LocalDateTime createdAt
//    ) {}
//2. Create DTOs (all fields required)
//    java// Perfect — all fields needed at creation
//    public record CreateUserRecord(
//            @NotBlank String name,
//            @Email String email,
//            @NotBlank String password
//    ) {}
//3. Search/Filter DTOs
//    java// Perfect — filter params don't change
//    public record UserFilterRecord(
//            String name,
//            String email,
//            String role
//    ) {}
//4. Event/Message objects
//    java// Perfect — events should never change
//    public record UserCreatedEvent(
//            String userId,
//            String email,
//            LocalDateTime timestamp
//    ) {}
//
//    When DTO Class is BETTER
//1. Update DTOs (partial update)
//    java// ✅ Better as class — fields are optional
//    public class UpdateUserDto {
//
//        @IsOptional
//        private String name;
//
//        @IsOptional
//        private String email;
//
//        @IsOptional
//        private String address;
//
//        // getters + setters
//    }
//
//    // Can send only what you want to update
//    UpdateUserDto dto = new UpdateUserDto();
//dto.setName("John"); // only update name
//// email and address stay null — ignored in service
//2. When you need inheritance
//    java// Records cannot extend other classes
//    // ❌ Not possible with record
//    public record AdminDto extends UserRecord { } // ERROR
//
//    // ✅ Possible with class
//    public class AdminDto extends UserDto {
//        private String adminLevel;
//    }
//3. When fields need to be built gradually
//            java// ✅ Builder pattern — not possible with record
//    UserDto dto = UserDto.builder()
//            .name("John")
//            .email("john@test.com")
//            .build();
//4. When you need Jackson custom serialization
//    java// More control with class
//    public class UserDto {
//        @JsonProperty("full_name")
//        private String name;
//
//        @JsonIgnore
//        private String password;
//    }
//```
//
//        ---
//
//        ## Side by Side Comparison
//
//| Feature | Record | DTO Class |
//            |---|---|---|
//            | Immutable | ✅ Yes | ❌ No |
//            | Partial update | ❌ No | ✅ Yes |
//            | Inheritance | ❌ No | ✅ Yes |
//            | Less boilerplate | ✅ Yes | ❌ No |
//            | Builder pattern | ❌ No | ✅ Yes |
//            | Response objects | ✅ Perfect | ✅ Works |
//            | Create objects | ✅ Perfect | ✅ Works |
//            | Update objects | ⚠️ Problematic | ✅ Perfect |
//            | Validation annotations | ✅ Works | ✅ Works |
//
//            ---
//
//            ## Simple Rule
//```
//    CREATE  → Record ✅  (all fields required anyway)
//    READ    → Record ✅  (immutable response)
//    UPDATE  → Class  ✅  (partial update needed)
//    DELETE  → Record ✅  (just need an ID)
//    FILTER  → Record ✅  (search params don't change)
//
//    Best Practice in Spring Boot
//            java// CREATE — use record
//    public record CreateUserRequest(
//            @NotBlank String name,
//            @Email    String email,
//            @Size(min=6) String password
//    ) {}
//
//    // UPDATE — use class
//    public class UpdateUserRequest {
//        private String name;     // optional
//        private String email;    // optional
//        private String address;  // optional
//        // getters + setters
//    }
//
//    // RESPONSE — use record
//    public record UserResponse(
//            String id,
//            String name,
//            String email,
//            String role
//    ) {}
//
//    Bottom line: Records are great for create and response DTOs. For update DTOs always use a regular class because partial updates require nullable optional fields which records handle poorly.


}
