package com.ishnit.user.service;

import com.ishnit.user.entity.User;
import com.ishnit.user.repository.UserRepository;
import com.ishnit.user.vo.Department;
import com.ishnit.user.vo.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserAndDepartment(Long userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Optional<User> user = userRepository.findById(userId);
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+ user.get().getDepartmentId(), Department.class);
//        Department department = restTemplate.getForObject("http://localhost:9001/departments/"+ user.get().getDepartmentId(), Department.class);
        vo.setUser(user.get());
        vo.setDepartment(department);
        return vo;
    }
}
