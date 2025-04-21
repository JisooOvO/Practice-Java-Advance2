package io.member.impl;

import io.member.Member;
import io.member.MemberRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DatMemberRepository implements MemberRepository {

    public static final String FILE_PATH = "temp/members-data.dat";

    @Override
    public void add(Member member) {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_PATH, true))) {
            // 구분자 없이 바이트 길이로 구분 가능
            dos.writeUTF(member.getId());
            dos.writeUTF(member.getName());
            dos.writeInt(member.getAge());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();

        try(DataInputStream dis = new DataInputStream(new FileInputStream(FILE_PATH))) {
            // 읽을 스트림이 있는지 확인
            while (dis.available() > 0) {
                Member member = new Member(dis.readUTF(), dis.readUTF(), dis.readInt());
                members.add(member);
            }

            return members;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
            // return List.of();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
