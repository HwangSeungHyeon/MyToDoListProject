package com.teamsparta.mytodolist.domain.comment.controller

import com.teamsparta.mytodolist.domain.comment.dto.AddCommentRequestDto
import com.teamsparta.mytodolist.domain.comment.dto.CommentResponseDto
import com.teamsparta.mytodolist.domain.comment.dto.DeleteCommentRequestDto
import com.teamsparta.mytodolist.domain.comment.dto.UpdateCommentRequestDto
import com.teamsparta.mytodolist.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/*
* Spring의 Web Layer의 일부
* Spring의 Service 레이어와 접근하기 위해서 사용하는 클래스
* Client의 요청(Request)을 받고, 응답(Response)을 주는 역할
*/
@RequestMapping("/todos/{todoId}/comments") // base URI 경로 하위에 해당하는 건 전부 CommentController가 담당하게 됨
@RestController //CommentController를 Controll layer를 담당하는 Bean으로 등록
class CommentController(
    private val commentService: CommentService
) {
    @GetMapping() //GET 메소드 핸들링, /todos/{todoId}/comments에 접근한다
    //선택한 할 일 카드에 작성된 댓글을 모두 가져오는 메소드
    //todoId를 argument로 받아서 Service layer로부터 CommentResponseDto를 리스트 형태로 받음
    //Service layer에서 전달된 DTO를 ResponseEntity로 감싸서 반환함(응답해줌)
    fun getCommentList(@PathVariable todoId: Long): ResponseEntity<List<CommentResponseDto>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getCommentList(todoId))
    }

    @PostMapping()//POST 메소드 핸들링, /todos/{todoId}/comments에 접근한다
    //선택한 할 일 카드에 댓글을 작성하는 메소드
    //todoId와 AddCommentRequestDto를 argument로 받아서 Service layer로부터 CommentResponseDto를 받음
    //Service layer에서 전달된 DTO를 ResponseEntity로 감싸서 반환함(응답해줌)
    fun addComment(
        @PathVariable todoId: Long,
        @RequestBody addCommentRequestDto: AddCommentRequestDto
    ): ResponseEntity<CommentResponseDto>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.addComment(todoId, addCommentRequestDto))
    }

    @PutMapping("/{commentId}") //PUT 메소드 핸들링, /todos/{todoId}/comments/{commentId}에 접근한다
    //선택한 할 일 카드에 작성된 댓글 중 하나를 선택해서 수정하는 메소드
    //todoId와 commentId, UpdateCommentRequestDto를 argument로 받아서 Service layer로부터 CommentResponseDto를 받음
    //Service layer에서 전달된 DTO를 ResponseEntity로 감싸서 반환함(응답해줌)
    fun updateComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @RequestBody updateCommentRequestDto: UpdateCommentRequestDto
    ): ResponseEntity<CommentResponseDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(todoId, commentId, updateCommentRequestDto))
    }

    @DeleteMapping("/{commentId}") //DELETE 메소드 핸들링, /todos/{todoId}/comments/{commentId}에 접근한다
    //선택한 할 일 카드에 작성된 댓글 중 하나를  선택해서 삭제하는 메소드
    //todoId와 commentId, DeleteCommentRequestDto를 argument로 받아서 해당 id에 해당하는 데이터를 삭제함
    //Service layer에서 전달된 DTO가 없으므로 비어있는 ResponseEntity를 반환함(응답해줌)
    fun deleteComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @RequestBody deleteCommentRequestDto: DeleteCommentRequestDto
    ): ResponseEntity<CommentResponseDto>{
        commentService.deleteComment(todoId, commentId, deleteCommentRequestDto)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}