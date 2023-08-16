package APMA.APMAproject.config.spring_security;

import APMA.APMAproject.constant.Role;

public interface UserEntity {

    public Long getId();
    public String getUsername();

    public Role getRole();

    public String getPassword();

    public void setUsername(String username);


    public void setPassword(String password);
}
