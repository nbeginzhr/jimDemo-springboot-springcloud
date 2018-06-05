package cn.haoyu.common.repository;

import cn.haoyu.common.entity.ChatContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by haoyu on 2018/5/24.
 */
@Repository
public interface ChatContentReposiroty extends MongoRepository<ChatContent,String> {
}
