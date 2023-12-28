package com.teamsparta.mytodolist.domain.comment.service

import com.teamsparta.mytodolist.domain.comment.dto.AddCommentRequestDto
import com.teamsparta.mytodolist.domain.comment.dto.CommentResponseDto
import com.teamsparta.mytodolist.domain.comment.dto.DeleteCommentRequestDto
import com.teamsparta.mytodolist.domain.comment.dto.UpdateCommentRequestDto
import com.teamsparta.mytodolist.domain.comment.model.CommentModel
import com.teamsparta.mytodolist.domain.comment.model.toRespond
import com.teamsparta.mytodolist.domain.comment.repository.CommentRepository
import com.teamsparta.mytodolist.domain.exception.ModelNotFoundException
import com.teamsparta.mytodolist.domain.todo.model.toResponse
import com.teamsparta.mytodolist.domain.todo.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service //CommentServiceImpl를 Service Layer를 담당하는 bean으로 설정
class CommentServiceImpl(
    private val todoRepository: TodoRepository,
    private val commentRepository: CommentRepository
): CommentService {

    //선택한 할 일 카드에 작성된 댓글을 모두 가져오는 메소드
    //Entity로 DB에서 값을 가져와서 응답(Response) DTO 리스트로 바꾸고, Controller로 전달
    override fun getCommentList(todoId: Long): List<CommentResponseDto> {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        return todo.comments.map { it.toRespond() }
    }

    /*
    * 선택한 할 일 카드에 댓글을 작성하는 메소드
    * Controller로부터 생성 요청(Request) DTO를 받는다.
    * 생성 요청(Request) DTO에 있는 값을 Entity에 담아서 DB로 전달
    * 그 후 응답(Response) DTO를 Controller에 전달
    */
    @Transactional
    override fun addComment(todoId: Long, addCommentRequestDto: AddCommentRequestDto): CommentResponseDto {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val comment = CommentModel(
            content = addCommentRequestDto.content,
            date = LocalDateTime.now(),
            name = addCommentRequestDto.name,
            password = addCommentRequestDto.password,
            todoId = todoId
        )
        todo.comments.add(comment)
        todoRepository.save(todo)

        return comment.toRespond()
    }

    /*
    * 선택한 할 일 카드에 작성된 댓글 하나를 선택하여 수정하는 메소드
    * Controller로부터 수정 요청(Request) DTO를 받는다.
    * 수정 요청(Request) DTO에 있는 값을 Entity에 담아서 DB로 전달
    * 그 후 응답(Response) DTO를 Controller에 전달
    */
    @Transactional
    override fun updateComment(
        todoId: Long,
        commentId: Long,
        updateCommentRequestDto: UpdateCommentRequestDto
    ): CommentResponseDto {
        val comment = commentRepository.findByTodoIdAndId(todoId, commentId) ?: throw ModelNotFoundException("Comment", commentId)
        if(comment.name != updateCommentRequestDto.name || comment.password != updateCommentRequestDto.password) throw IllegalStateException("Name or password is incorrect")

        comment.content = updateCommentRequestDto.content
        return commentRepository.save(comment).toRespond()
    }

    /*
    * 선택한 할 일 카드에 작성된 댓글 하나를 선택하여 삭제하는 메소드
    * Controller로부터 삭제 요청(Request) DTO를 받는다.
    * 삭제 요청(Request) DTO에 있는 값을 Entity에 담아서 DB로 전달
    * 그 후 응답(Response) DTO를 Controller에 전달
    */
    @Transactional
    override fun deleteComment(
        todoId: Long,
        commentId: Long,
        deleteCommentRequestDto: DeleteCommentRequestDto
    ): CommentResponseDto {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val comment = commentRepository.findByTodoIdAndId(todoId, commentId) ?: throw ModelNotFoundException("Comment", commentId)
        if(comment.name != deleteCommentRequestDto.name || comment.password != deleteCommentRequestDto.password) throw IllegalStateException("Name or password is incorrect")

        todo.comments.remove(comment)
        todoRepository.save(todo)
        return comment.toRespond()
    }
}