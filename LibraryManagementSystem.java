import java.util.HashMap;
import java.util.Scanner;

class Book {
    public String bookName;
    public int copies;

    public Book(String bookName, int copies) {
        this.bookName = bookName;
        this.copies = copies;
    }
}

class Member {
    public String memberId;
    public String memberName;
    public int toPay;
    public String memberBook;
    public String borrowDate;

    public Member(String memberId, String memberName, int toPay, String memberBook, String borrowDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.toPay = toPay;
        this.memberBook = memberBook;
        this.borrowDate = borrowDate;
    }
}

class Library {
    public HashMap<String, Book> books;
    public HashMap<String, Member> members;

    public Library() {
        books = new HashMap<>();
        members = new HashMap<>();
    }

    // Add a new book
    public void addBook(Book book) {
        books.put(book.bookName, book);
        System.out.printf("Book added: %s with %d copies\n", book.bookName, book.copies);
    }

    // Delete a book
    public void deleteBook(String bookName) {
        if (books.remove(bookName) != null) {
            System.out.printf("Book %s has been removed\n", bookName);
        } else {
            System.out.println("Book not found.");
        }
    }

    // Add a new member
    public void addMember(Member member) {
        members.put(member.memberId, member);
        System.out.printf("Member added: %s with ID %s\n", member.memberName, member.memberId);
    }

    // Delete a member
    public void deleteMember(String memberId) {
        Member member = members.get(memberId);
        if (member != null && member.memberBook.equals("")) {
            members.remove(memberId);
            System.out.printf("Member with ID %s has been removed\n", memberId);
        } else {
            System.out.println("Member not found or has borrowed a book.");
        }
    }

    // Search for a book
    public void searchBook(String bookName) {
        Book book = books.get(bookName);
        if (book != null) {
            System.out.printf("Book found: %s with %d copies available\n", book.bookName, book.copies);
        } else {
            System.out.println("No book found with that name.");
        }
    }

    // Borrow a book
    public void borrowBook(String memberId, String bookName, String borrowDate) {
        Member member = members.get(memberId);
        Book book = books.get(bookName);

        if (member != null && book != null && book.copies > 0) {
            member.memberBook = bookName;
            member.borrowDate = borrowDate;
            book.copies--;
            System.out.printf("%s borrowed the book %s on %s\n", member.memberName, bookName, borrowDate);
        } else {
            System.out.println("Borrowing failed. Either the book or member doesn't exist or no copies are available.");
        }
    }

    // Return a book
    public void returnBook(String memberId) {
        Member member = members.get(memberId);
        if (member != null && !member.memberBook.equals("")) {
            Book book = books.get(member.memberBook);
            book.copies++;
            System.out.printf("%s has returned the book %s\n", member.memberName, member.memberBook);
            member.memberBook = "";
        } else {
            System.out.println("No book to return.");
        }
    }

    // View all available books
    public void viewAvailableBooks() {
        System.out.println("Available books in the library:");
        books.forEach((bookName, book) -> System.out.printf("%s - %d copies available\n", bookName, book.copies));

    }

    // View all members
    public void viewAllMembers() {
        System.out.println("List of all members:");
        members.values().forEach(member -> System.out.printf("ID: %s, Name: %s, Book: %s\n", member.memberId, member.memberName, member.memberBook));
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Choose your action :");
            System.out.println("1 - Add a New Book");
            System.out.println("2 - Delete a Book");
            System.out.println("3 - Add a New Member");
            System.out.println("4 - Delete a Member");
            System.out.println("5 - Borrow a Book");
            System.out.println("6 - Return a Book");
            System.out.println("7 - Search Book");
            System.out.println("8 - View All Available Books");
            System.out.println("9 - View All Members");
            System.out.println("10 - EXIT");
            System.out.print("Enter Your Choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book name: ");
                    String bookName = sc.nextLine();
                    System.out.print("Enter book copies: ");
                    int copies = sc.nextInt();
                    library.addBook(new Book(bookName, copies));
                    break;

                case 2:
                    System.out.print("Enter book name to delete: ");
                    String deleteBookName = sc.nextLine();
                    library.deleteBook(deleteBookName);
                    break;

                case 3:
                    System.out.print("Enter member name: ");
                    String memberName = sc.nextLine();
                    System.out.print("Enter member ID: ");
                    String memberId = sc.nextLine();
                    library.addMember(new Member(memberId, memberName, 0, "", ""));
                    break;

                case 4:
                    System.out.print("Enter member ID to delete: ");
                    String deleteMemberId = sc.nextLine();
                    library.deleteMember(deleteMemberId);
                    break;

                case 5:
                    System.out.print("Enter book name to borrow: ");
                    String borrowBookName = sc.nextLine();
                    System.out.print("Enter member ID: ");
                    String borrowMemberId = sc.nextLine();
                    System.out.print("Enter borrow date: ");
                    String borrowDate = sc.nextLine();
                    library.borrowBook(borrowMemberId, borrowBookName, borrowDate);
                    break;

                case 6:
                    System.out.print("Enter member ID to return book: ");
                    String returnMemberId = sc.nextLine();
                    library.returnBook(returnMemberId);
                    break;

                case 7:
                    System.out.print("Enter book name to search: ");
                    String searchBookName = sc.nextLine();
                    library.searchBook(searchBookName);
                    break;

                case 8:
                    library.viewAvailableBooks();
                    break;

                case 9:
                    library.viewAllMembers();
                    break;

                case 10:
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
