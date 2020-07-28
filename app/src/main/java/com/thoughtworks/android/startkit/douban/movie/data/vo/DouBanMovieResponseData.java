package com.thoughtworks.android.startkit.douban.movie.data.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by zhuang on 12/03/2017.
 */

@Builder
@AllArgsConstructor
@Getter
@Setter
public class DouBanMovieResponseData {
    private int count;
    private int start;
    private int total;
    @Getter
    @Setter
    private List<MovieBean> subjects;

    @Builder
    @AllArgsConstructor
    @Getter
    @Setter
    public static class MovieBean {

        private RatingBean rating;
        private String subtype;
        private String origin_title;
        private Integer collect_count;
        private Integer year;
        private ImagesBean images;

        private String alt;
        private String id;
        private String title;
        private List<DirectorBean> directors;
        private List<String> genres;

        @Getter
        @Setter
        public static class RatingBean {
            /**
             * max : 10
             * numRaters : 16988
             * average : 9.1
             * min : 0
             */

            private int max;
            private int numRaters;
            private String average;
            private int min;
        }

        @Getter
        @Setter
        public static class ImagesBean{


            /**
             * small : https://img3.doubanio.com/spic/s6807265.jpg
             * large : https://img3.doubanio.com/lpic/s6807265.jpg
             * medium : https://img3.doubanio.com/mpic/s6807265.jpg
             */

            private String small;
            private String large;
            private String medium;
        }

        @Getter
        @Setter
        public static class DirectorBean {
            private String name;
        }
    }
}
