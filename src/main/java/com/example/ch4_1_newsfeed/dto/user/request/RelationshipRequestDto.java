package com.example.ch4_1_newsfeed.dto.user.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RelationshipRequestDto {

    private final boolean isRelated;
}
