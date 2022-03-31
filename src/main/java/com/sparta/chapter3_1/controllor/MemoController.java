package com.sparta.chapter3_1.controllor;

import com.sparta.chapter3_1.dto.MemoRequestDto;
import com.sparta.chapter3_1.model.Memo;
import com.sparta.chapter3_1.repository.MemoRepository;
import com.sparta.chapter3_1.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController

public class MemoController {

    private final MemoRepository memoRepository;
    private final MemoService memoService;

    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        System.out.println(memo);
        return memoRepository.save(memo);
    }

    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    @PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        memoService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        memoRepository.deleteById(id);
        return id;
    }

    @GetMapping("/api/memos/detail/{id}")
    public Memo getDetail (@PathVariable Long id) {
        return memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("null"));
    }


}