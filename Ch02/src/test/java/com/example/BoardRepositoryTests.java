package com.example;

import com.example.domain.Board;
import com.example.persistence.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepo;

    @Test
    public void inspect() {
        // 실제 객체의 클래스 이름
        Class<?> clz = boardRepo.getClass();

        System.out.println(clz.getName());

        // 클래스가 구현하고 있는 인터페이스 목록
        Class<?>[] interfaces = clz.getInterfaces();

        Stream.of(interfaces).forEach(inter -> System.out.println(inter.getName()));

        // 클래스의 부모 클래스
        Class<?> superClasses = clz.getSuperclass();

        System.out.println(superClasses.getName());

    }

    @Test
    public void testInsert() {//엔터티 추가
        Board board = new Board();
        board.setTitle("게시물의 제목");
        board.setContent("게시물 내용 넣기 ...");
        board.setWriter("user00");

        boardRepo.save(board);
    }

    @Test
    public void testRead() {//엔터티 읽기
        boardRepo.findById(1L).ifPresent((board)->{
            System.out.println(board);
        });
    }

    @Test
    public void testUpdate() {// 엔터티 업데이트
        System.out.println("======Read First======");
        Board board = boardRepo.findById(3L).get();

        System.out.println("=====Update Title=====");
        board.setTitle("수정된 제목입니다");

        System.out.println("======Call Save()=====");
        boardRepo.save(board);
    }

    @Test
    public void testDelete() {//엔터티 삭제
        System.out.println("DELETE ENTITY");
        boardRepo.deleteById(1L);
    }
}
