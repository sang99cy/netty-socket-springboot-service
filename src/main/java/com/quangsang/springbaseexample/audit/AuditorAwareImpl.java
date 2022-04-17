//package com.quangsang.springbaseexample.audit;
//
//import org.springframework.data.domain.AuditorAware;
//
//import java.util.Optional;
//
//public class AuditorAwareImpl implements AuditorAware<String> {
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        return Optional.of("admin");
//        //ham nay se luu thong tin user dang nhap khi su dung spring security, hien tai fix cung
//    }
//
//    // ------------------ Use below code for spring security --------------------------
//
///*class SpringSecurityAuditorAware implements AuditorAware<User> {
//
// public User getCurrentAuditor() {
//
//  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//  if (authentication == null || !authentication.isAuthenticated()) {
//   return null;
//  }
//
//  return ((MyUserDetails) authentication.getPrincipal()).getUser();
// }
//}*/
//}
