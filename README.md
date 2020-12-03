# library-management

This is a spring boot project. 
We have 2 services 
1.) book 
   
   It contains all the details that are related to book like book name, author name, categories, barcode of book. 
    
    We have following endpoints
   
    EndPoint    Method    Description
    /author :    GET    : Accepts String argument of author name
    /category:   GET    : Accepts String argument of category
    
    Once we have added author and category we can insert book.
    EndPoint            Method    Description
    /book :              POST   : It accepts bookName, PDF url of the book, description, authors, category, barcode of books. 
    
    Example of what the header will look like
    {"bookName":"Harry Potter","pdfUrl":"abc","description":"Harry Potter is a series of seven fantasy novels written by British author J. K. Rowling.", "authors":[{"id":0}],"categories":[{"id":1},{"id":3}],"bookBarcode":[{"id":1},{"id":"2"}]}
    
    /book              :  GET   : It will return details about the book that are present in the database.
    
    /book/{book-name}  :  GET   : It will use like query to check whether such a book is present or not.
    
    /book/authors      :  GET   : It will return list of all the authors that are present in the database.
    
    /book/author/{name}:  GET   : It will return list of books written by the author.
    
    /book/categories   :  GET   : It will return list of all the categories that are available in the database.
    
    /book/category/{name} GET   : It will return set of books matching that category.
    
2.) book-management

    It contains details related to book management like deposit book, issue book.
    
    EndPoint        Method    Description
    /issuebook   :  POST    :  It requires bookId and studentID. BookId is the book that student wants to issue.
    /depositbook :  POST    :  It requires bookBarcode and studentId. 
    
 Cool part here is as we can see that book-management has no idea about bookId, bookBarcode, available books so to solve this problem saga patterns come into picture.
 We have used Choreographic Based SAGA pattern and ActiveMQ for communication.
 
Whenever we try to issue a book. The status will be in pending state and an message for availbility will be send to book service it will check if book is available
or not and will send respective message.

Same goes when we try to deposit a book.

For Running this project you just need to change the database name and database URL.

Before running this project you must run activeMQ for communication between two services.
