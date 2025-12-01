package org.nhihuynh.models;


import lombok.*;


@Data
@NoArgsConstructor // Needed for Jackson to create the empty object first
@AllArgsConstructor
public class User {
    private String name;
    private String gender;
    private String country;

    public static void main(String[] args) {
        User u = new User();
        System.out.println(u.getName());
    }

}
