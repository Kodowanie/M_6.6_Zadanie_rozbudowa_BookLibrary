package testing.library;

import net.bytebuddy.matcher.FilterableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BookDirectoryTestSuite {
    @Mock
    private LibraryDatabase libraryDatabaseMock;

    private List <Book> generateListOfNBooks (int booksQuantity) {
        List<Book> resultList = new ArrayList<>();
        for (int n = 1 ; n <=booksQuantity ; n++){
            Book theBook = new Book("Title " + n, "Author " + n, 1970 + n);
            resultList.add(theBook);
        }
        return resultList;
    }



    @Nested
    @DisplayName("test BookLibrary method listBooksWithCondition")
    class testlistBooksWithCondition{
        @Test
        void testListBooksWithConditionsReturnList() {
            //Given
            BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
            List<Book> resultListOfBooks = new ArrayList<>();
            Book book1 = new Book("Secrets of Alamo", "John Smith", 2008);
            Book book2 = new Book("Secretaries and Directors", "Dilbert Michigan", 2012);
            Book book3 = new Book("Secret life of programmers", "Steve Wolkowitz", 2016);
            Book book4 = new Book("Secrets of Java", "Ian Tenewitch", 2010);
            resultListOfBooks.add(book1);
            resultListOfBooks.add(book2);
            resultListOfBooks.add(book3);
            resultListOfBooks.add(book4);
            when(libraryDatabaseMock.listBooksWithCondition("Secret")).thenReturn(resultListOfBooks);
            //When
            List<Book> theListOfBooks  = bookLibrary.listBooksWithCondition("Secret");
            //Then
            Assertions.assertEquals(4,theListOfBooks.size());
        }

        @Test
        void testListBooksWithConditionMoreThan20() {
            //Given
            BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
            List <Book> resultListOf0Books = new ArrayList<>();
            List <Book> resultListOf15Books = generateListOfNBooks(15);
            List <Book> resultListOf40Books = generateListOfNBooks(40);
            when(libraryDatabaseMock.listBooksWithCondition(anyString()))
                    .thenReturn(resultListOf15Books);
            when(libraryDatabaseMock.listBooksWithCondition("ZeroBooks"))
                    .thenReturn(resultListOf0Books);
            when(libraryDatabaseMock.listBooksWithCondition("FortyBooks"))
                    .thenReturn(resultListOf40Books);
            //When
            List <Book> theListOfBooks0 = bookLibrary.listBooksWithCondition("ZeroBooks");
            List <Book> theListOfBooks15 = bookLibrary.listBooksWithCondition("Any title");
            List <Book> theListOfBooks40 = bookLibrary.listBooksWithCondition("FortyBooks");
            //Then
            Assertions.assertEquals(0,theListOfBooks0.size());
            Assertions.assertEquals(15,theListOfBooks15.size());
            Assertions.assertEquals(0,theListOfBooks40.size());

        }

        @Test
        void testListBooksWithConditionFragmentShorterThan3() {
            //Given
            LibraryDatabase libraryDatabaseMock = mock(LibraryDatabase.class);
            BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
            //When
            List <Book> theListOfBooks10 = bookLibrary.listBooksWithCondition("An");
            //Then
            Assertions.assertEquals(0,theListOfBooks10.size());
            Mockito.verify(libraryDatabaseMock,times(0))
                    .listBooksWithCondition(anyString());
        }
    }

    @Nested
    @DisplayName("test BookLibrary method listBooksInHandsOf")
    class testListBooksInHandsOf{
        @Test
        void testBorrow0Books(){
           //Given
            BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
            LibraryUser libraryUser1 = new LibraryUser("a","b","id123");
            List<Book> resultListOf0Books = new ArrayList<>();
            when(libraryDatabaseMock.listBooksInHandsOf(libraryUser1)).thenReturn(resultListOf0Books);
           //When
            List <Book> theListOfBooks1 = bookLibrary.listBooksInHandsOf(libraryUser1);
           //Then
            Assertions.assertEquals(0,theListOfBooks1.size());
        }

        @Test
        void testBorrow1Books(){
            //Given
            BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
            LibraryUser libraryUser1 = new LibraryUser("a","b","id123");
            List <Book> resultListOf1Books = generateListOfNBooks(1);
            HashMap <LibraryUser , List<Book>> bookInUserHand = new HashMap<>();
            bookInUserHand.put(libraryUser1,resultListOf1Books);
            when(libraryDatabaseMock.listBooksInHandsOf(libraryUser1)).thenReturn(resultListOf1Books);
            //When
            List <Book> theListOfBooks1 = bookLibrary.listBooksInHandsOf(libraryUser1);
            //Then
            Assertions.assertEquals(1,theListOfBooks1.size());
        }

        @Test
        void testBorrow5Books(){
           //Given
            BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
            LibraryUser libraryUser1 = new LibraryUser("a","b","id123");
            List <Book> resultListOf5Books = generateListOfNBooks(5);
            HashMap <LibraryUser , List<Book>> bookInUserHand = new HashMap<>();
            bookInUserHand.put(libraryUser1,resultListOf5Books);
            when(libraryDatabaseMock.listBooksInHandsOf(libraryUser1)).thenReturn(resultListOf5Books);
            //When
            List <Book> theListOfBooks1 = bookLibrary.listBooksInHandsOf(libraryUser1);
            //Then
            Assertions.assertEquals(5,theListOfBooks1.size());

        }

    }


}
