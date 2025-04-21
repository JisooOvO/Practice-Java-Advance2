package io.member.impl;

import io.member.Member;
import io.member.MemberRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectMemberRepository implements MemberRepository {

    public static final String FILE_PATH = "temp/members-obj.dat";

    @Override
    public void add(Member member) {
        List<Member> members = findAll();
        members.add(member);

        // 객체 집합을 통으로 하나의 파일에 저장
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(members);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> findAll() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            // 클래스 정보를 토대로 역직렬화
            return (List<Member>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
            // return List.of() -> immutable -> add 불가 -> ERROR
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
