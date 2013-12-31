//package models;
//
//    import play.db.ebean.Model;
//    import javax.persistence.*;
//
//    @Entity
//    public class Bookmark extends Model {
//
//        @Id
//        public Long id;
//        public String title;
//        public String url;
//        public String details;
//
////        @ManyToOne
////        public Category category;
//
//        public static Finder<Long, Bookmark> find = 
//                new Finder<Long, Bookmark>(Long.class, Bookmark.class);
//    }