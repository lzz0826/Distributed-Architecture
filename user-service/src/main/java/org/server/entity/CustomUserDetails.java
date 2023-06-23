package org.server.entity;

import java.util.Collection;
import java.util.List;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Builder

public class CustomUserDetails implements UserDetails {

  /**
   * 用戶ID
   */
  private String id;

  /**
   * 用户名
   */
  private String username;

  /**
   * 密码
   */
  private String password;

  /**
   * 是否可用
   */
  private Boolean enabled;

  /**
   * 用户所拥有的权限
   */
  private List<? extends GrantedAuthority> authorities;


  /**
   * 用户的账号是否过期,过期的账号无法通过授权验证. true 账号未过期
   */
  private Boolean accountNonExpired;

  /**
   * 用户的账户是否被锁定,被锁定的账户无法通过授权验证. true 账号未锁定
   */
  private Boolean accountNonLocked;

  /**
   * 用户的凭据(pasword) 是否过期,过期的凭据不能通过验证. true 没有过期,false 已过期
   */
  private Boolean credentialsNonExpired;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public String getId() {
    return id;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
