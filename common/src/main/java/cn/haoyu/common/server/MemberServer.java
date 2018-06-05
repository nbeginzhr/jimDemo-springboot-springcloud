package cn.haoyu.common.server;

import cn.haoyu.common.entity.Member;
import cn.haoyu.common.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by haoyu on 2018/5/24.
 */
@Component("memberServer")
public class MemberServer {

    @Autowired
    private MemberRepository memberRepository;

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public Member fetch(String hid) {
        return memberRepository.findOne(hid);
    }

    public Member findByAccountHid(String accountHid) {
        return memberRepository.findByAccountHid(accountHid);
    }

    public Boolean changeOnline(Boolean online, String hid) {
        Member member = fetch(hid);
        if (null == member) return false;
        member.setOnline(online);
        save(member);
        return true;
    }

}
