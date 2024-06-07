package com.blogapp.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private long id;
    @NotEmpty
    @Size(min = 3, message = "title should be atleast 3 characters")
    private String title;

    @NotEmpty
    @Size(min = 3,message = "description should be atleast 3 characters")
    private String description;

    private String content;

    @Email
    private String email;
    @Size(min = 10,max = 10,message = "Mobile number should be 10 digits")
    private String mobile;

}
