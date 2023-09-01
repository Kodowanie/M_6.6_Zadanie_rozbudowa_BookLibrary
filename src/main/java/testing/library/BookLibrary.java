package testing.library;

import java.util.*;

public class BookLibrary {

    private LibraryDatabase libraryDatabase;

    public BookLibrary(LibraryDatabase libraryDatabase) {
        this.libraryDatabase = libraryDatabase;
    }

    public List<Book> listBooksWithCondition (String titleFragment){
        List <Book> bookList = new ArrayList<>();
        if (titleFragment.length()<3) return bookList;
        List <Book> resultList = libraryDatabase
                .listBooksWithCondition(titleFragment);
        if (resultList.size() > 20) return bookList;
        bookList = resultList;
        return bookList;
    }

    public List<Book> listBooksInHandsOf(LibraryUser libraryUser){
        LibraryUser libraryUsersList = new LibraryUser(libraryUser.getFirstname(),
                libraryUser.getLasname(),libraryUser.getPeselId());
        List <Book> booklist = new ArrayList<>();
        HashMap <LibraryUser , List<Book>> bookInUserHand = new HashMap<>();
        bookInUserHand.put(libraryUser,booklist);
        for (List <Book> showBookInHand : bookInUserHand.values()){
            if (showBookInHand.size() <= 0){
                List <Book> resultList = libraryDatabase.listBooksInHandsOf(libraryUser);
                booklist = resultList;
                return booklist;
            }else{
                List <Book> resultList = libraryDatabase.listBooksInHandsOf(libraryUser);
                booklist = resultList;
                return booklist;
            }
        }
        return booklist;
    }
}
