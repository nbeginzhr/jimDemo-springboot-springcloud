package cn.haoyu.common.repository;

import cn.haoyu.common.entity.Haccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by haoyu on 2018/5/26.
 */
public interface HaccountRepository extends JpaRepository<Haccount,String> {

    Haccount findByUsernameAndPassword(String username,String encodedPwd);
}
