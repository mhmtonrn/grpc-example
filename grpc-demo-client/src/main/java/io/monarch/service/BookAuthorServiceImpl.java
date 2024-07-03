package io.monarch.service;

import io.grpc.stub.StreamObserver;
import io.monarch.Author;
import io.monarch.Book;
import io.monarch.BookAuthorServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.google.protobuf.Descriptors;

@Service
public class BookAuthorServiceImpl implements BookAuthorService {

  @GrpcClient("grpc-service")
  BookAuthorServiceGrpc.BookAuthorServiceBlockingStub synchronousClient;

  @GrpcClient("grpc-service")
  BookAuthorServiceGrpc.BookAuthorServiceStub asynchronousClient;

  public Map<Descriptors.FieldDescriptor, Object> getAuthor(int authorId) {
    Author authorRequest = Author.newBuilder().setAuthorId(authorId).build();
    Author authorResponse = synchronousClient.getAuthor(authorRequest);
    System.out.println(authorResponse);
    return authorResponse.getAllFields();
  }

  public CompletableFuture<List<Map<Descriptors.FieldDescriptor, Object>>> getBooksByAuthor(int authorId) throws InterruptedException {
    CompletableFuture<List<Map<Descriptors.FieldDescriptor, Object>>> completableFuture = new CompletableFuture<>();
    final Author authorRequest = Author.newBuilder().setAuthorId(authorId).build();
    final List<Map<Descriptors.FieldDescriptor, Object>> response = new ArrayList<>();
    asynchronousClient.getBooksByAuthor(authorRequest, new StreamObserver<Book>() {
      @Override
      public void onNext(Book book) {
        response.add(book.getAllFields());
      }

      @Override
      public void onError(Throwable throwable) {
        completableFuture.completeExceptionally(throwable);
      }

      @Override
      public void onCompleted() {
        completableFuture.complete(response);
      }
    });
    return completableFuture;
  }

}