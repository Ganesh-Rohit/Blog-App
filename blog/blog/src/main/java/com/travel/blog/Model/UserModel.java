package com.travel.blog.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {

    private ObjectId id;

    private String userId;

    private String firstName;

    private String lastName;

    private String emailId;

    private String passWord;

    private String token;

}
