package com.teamsparta.mytodolist.domain.comment.service

import com.teamsparta.mytodolist.domain.comment.dto.AddCommentRequestDto
import com.teamsparta.mytodolist.domain.comment.dto.CommentResponseDto
import com.teamsparta.mytodolist.domain.comment.dto.DeleteCommentRequestDto
import com.teamsparta.mytodolist.domain.comment.dto.UpdateCommentRequestDto

/*
* Spring의 Layer의 일부
* Service Layer : Web Layer 하위에 존재하는 Layer
* 트랜잭션(Transaction) 경계의 역할
* Application Service : 응답(response)을 WebLayer(그중에서도 controller)에 넘겨주는 역할
*/
interface CommentService {
    //선택한 할 일 카드에 작성된 댓글을 모두 가져오는 메소드
    //Entity로 DB에서 값을 가져와서 응답(Response) DTO 리스트로 바꾸고, Controller로 전달
    fun getCommentList(todoId: Long): List<CommentResponseDto>

    /*
    * 선택한 할 일 카드에 댓글을 작성하는 메소드
    * Controller로부터 생성 요청(Request) DTO를 받는다.
    * 생성 요청(Request) DTO에 있는 값을 Entity에 담아서 DB로 전달
    * 그 후 응답(Response) DTO를 Controller에 전달
    */
    fun addComment(todoId: Long, addCommentRequestDto: AddCommentRequestDto): CommentResponseDto


    /* 
    * 선택한 할 일 카드에 작성된 댓글 하나를 선택하여 수정하는 메소드
    * Controller로부터 수정 요청(Request) DTO를 받는다.
    * 수정 요청(Request) DTO에 있는 값을 Entity에 담아서 DB로 전달
    * 그 후 응답(Response) DTO를 Controller에 전달
    */
    fun updateComment(todoId: Long, commentId: Long, updateCommentRequestDto: UpdateCommentRequestDto): CommentResponseDto

    /*
    * 선택한 할 일 카드에 작성된 댓글 하나를 선택하여 삭제하는 메소드
    * Controller로부터 삭제 요청(Request) DTO를 받는다.
    * 삭제 요청(Request) DTO에 있는 값을 Entity에 담아서 DB로 전달
    * 그 후 응답(Response) DTO를 Controller에 전달
    */
    fun deleteComment(todoId: Long, commentId: Long, deleteCommentRequestDto: DeleteCommentRequestDto)
}