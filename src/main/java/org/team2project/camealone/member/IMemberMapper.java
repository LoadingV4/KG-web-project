package org.team2project.camealone.member;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface IMemberMapper {
    @Select("SELECT * FROM userinfo WHERE id = #{id}")
    MemberDTO login(String id);

    @Insert("INSERT INTO userinfo (id, name, password, email, phone) VALUES (#{id}, #{name}, #{password}, #{email}, #{phone})")
    int addMember(MemberDTO memberDTO);
}
