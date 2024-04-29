package com.exam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long roleId;
    private  String roleName;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "role")
    @JsonIgnore
    private Set<UserRole> userRoles =new HashSet<>();

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Role() {

    }

    public long getRoleId() {
        return roleId;
    }


    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Role(long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
}



//====================================================================
// package com.exam.model;
//
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//
//@Entity
//public class Role
//{
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private  long roleId;
//    private  String roleName;
//
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "role")
//    @JsonIgnore
//    private Set<UserRole> userRoles =new HashSet<>();
//
//    public Set<UserRole> getUserRoles() {
//        return userRoles;
//    }
//
//    public void setUserRoles(Set<UserRole> userRoles) {
//        this.userRoles = userRoles;
//    }
//
//    public Role() {
//
//    }
//
//    public long getRoleId() {
//        return roleId;
//    }
//
//
//    public void setRoleId(long roleId) {
//        this.roleId = roleId;
//    }
//
//    public String getRoleName() {
//        return roleName;
//    }
//
//    public void setRoleName(String roleName) {
//        this.roleName = roleName;
//    }
//
//    public Role(long roleId, String roleName) {
//        this.roleId = roleId;
//        this.roleName = roleName;
//    }
//}
////
////@Entity
////@Table(name = "roles")
////public class Role {
////
////    @Id
////    private Long roleId;
////    private String roleName;
////
////
////    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "role")
////    private Set<UserRole> userRoles=new HashSet<>();
////
////
////    public Role() {
////    }
////
////
////    public Set<UserRole> getUserRoles() {
////        return userRoles;
////    }
////
////    public void setUserRoles(Set<UserRole> userRoles) {
////        this.userRoles = userRoles;
////    }
////
////    public Role(Long roleId, String roleName) {
////        this.roleId = roleId;
////        this.roleName = roleName;
////    }
////
////    public Long getRoleId() {
////        return roleId;
////    }
////
////    public void setRoleId(Long roleId) {
////        this.roleId = roleId;
////    }
////
////    public String getRoleName() {
////        return roleName;
////    }
////
////    public void setRoleName(String roleName) {
////        this.roleName = roleName;
////    }
////}
