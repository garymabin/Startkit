package com.thoughtworks.android.startkit.douban.books.data.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by zhuang on 12/03/2017.
 */

@Getter
@AllArgsConstructor
public class BookData {
    private List<BookItem> books;
    private int total;
    private int count;
    private int start;
}
