package com.thoughtworks.android.startkit.douban.movie.data.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by zhuang on 12/03/2017.
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieData {
    private List<MovieItem> books;
    private int total;
    private int count;
    private int start;
}
