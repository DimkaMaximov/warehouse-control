package liga.warehouse.warehousecontrol.service.impl;

import liga.warehouse.warehousecontrol.api.UserService;
import liga.warehouse.warehousecontrol.dto.UserEntityDto;
import liga.warehouse.warehousecontrol.model.UserEntity;
import liga.warehouse.warehousecontrol.repository.UserEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserEntityRepository repository;

    public UserServiceImpl(UserEntityRepository repository) {
        this.repository = repository;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserEntityDto> findAll() {
        List<UserEntity> userList = repository.findAll();
        return userList.stream()
                .map(el -> modelMapper.map(el, UserEntityDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserEntityDto findById(Long userId) {
        UserEntity user = repository.findById(userId);
        return modelMapper.map(user, UserEntityDto.class);
    }

    @Override
    public UserEntityDto findByEmail(String email) {
        UserEntity user = repository.findByEmail(email);
        return modelMapper.map(user, UserEntityDto.class);
    }

    @Override
    public void insert(UserEntityDto userEntityDto) {
        UserEntity userEntity = modelMapper.map(userEntityDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        if (userEntity.getId() == null) repository.insert(userEntity);
        else repository.updateById(userEntity);
    }

    @Override
    public void deleteById(Long userId) {
        repository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }
}