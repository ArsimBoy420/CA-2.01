/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class UserDTO {
    private long id;
    private String str1;
    private String str2;

    public UserDTO(String dummyStr1, String dummyStr2) {
        this.str1 = dummyStr1;
        this.str2 = dummyStr2;
    }
    
    public static List<UserDTO> getDtos(List<User> rms){
        List<UserDTO> rmdtos = new ArrayList();
        rms.forEach(rm->rmdtos.add(new UserDTO(rm)));
        return rmdtos;
    }


    public UserDTO(User rm) {
        if(rm.getId() != null)
            this.id = rm.getId();
        this.str1 = rm.getDummyStr1();
        this.str2 = rm.getDummyStr2();
    }

    public String getDummyStr1() {
        return str1;
    }

    public void setDummyStr1(String dummyStr1) {
        this.str1 = dummyStr1;
    }

    public String getDummyStr2() {
        return str2;
    }

    public void setDummyStr2(String dummyStr2) {
        this.str2 = dummyStr2;
    }

    @Override
    public String toString() {
        return "RenameMeDTO{" + "id=" + id + ", str1=" + str1 + ", str2=" + str2 + '}';
    }
    
    
    
    //sup
    
    
}
