package io.monarch.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.google.protobuf.Descriptors;

public interface BookAuthorService {

  Map<Descriptors.FieldDescriptor, Object> getAuthor(int authorId);

  CompletableFuture<List<Map<Descriptors.FieldDescriptor, Object>>> getBooksByAuthor(int authorId) throws InterruptedException;
}