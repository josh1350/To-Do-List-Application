package nice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nice.models.Role;
import nice.models.RoleDao;
@Service
public class RoleService {
    private RoleDao roleRepository;

    @Autowired
    public RoleService(RoleDao roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
