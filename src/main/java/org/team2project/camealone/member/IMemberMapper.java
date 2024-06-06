package org.team2project.camealone.member;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface IMemberMapper {
    @Select("SELECT * FROM userinfo WHERE id = #{id}")
    MemberDTO login(String id);

    @Select("SELECT * FROM userinfo WHERE email = #{email}")
    MemberDTO findByEmail(String email);

    @Select("SELECT * FROM userinfo WHERE phone = #{phone}")
    MemberDTO findByPhone(String phone);

    @Insert("INSERT INTO userinfo (id, name, password, email, phone) VALUES (#{id}, #{name}, #{password}, #{email}, #{phone})")
    int addMember(MemberDTO memberDTO);

    @Delete("DELETE FROM userinfo WHERE id = #{id}")
    int deleteMember(String id);
}
