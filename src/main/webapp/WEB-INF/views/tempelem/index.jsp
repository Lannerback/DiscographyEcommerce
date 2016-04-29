<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
    <table class="table-album">
        <caption>Albums</caption>
        <thead>
            <tr>
                <th>Artista</th>
                <th>Album</th> 
                <th>Length</th> 
                <th>Year</th> 
                <th>Genre</th> 
                <th>Label</th> 
                <th>Producer</th>                    
            </tr>
        </thead>  
        <tbody>
        <c:forEach var="cd" items="${albums}" varStatus="loop">
            <tr>
                <td>
                    ${cd.artist.name}
                </td>
                <td>
                    ${cd.title}
                </td> 
                <td>
                    ${cd.length}
                </td> 
                <td>
                    ${cd.year}
                </td> 
                <td>
                    ${cd.genre}
                </td>
                <td>
                    ${cd.label}
                </td> 
                <td>
                    ${cd.producer}
                </td>               

                <td>                        
                    <form method="POST" action="${pageContext.request.contextPath}/showalbum/${cd.uid}">
                        <input type="submit" value="Detail" />
                    </form>

                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="container">
    <table class="table-album">
        <h1>Artists</h1>
        <thead>
            <tr>
                <th>Name</th>
                <th>Surname</th>                                         
            </tr>
        </thead>    
        <c:forEach var="artist" items="${artists}" varStatus="loop">
            <tr>
                <td>
                    ${artist.name}
                </td>
                <td>
                    ${artist.surname}
                </td>                    
                <td>                        
                    <form method="POST" action="${pageContext.request.contextPath}/showartist/${artist.uid}">
                        <input type="submit" value="showdetail" />
                    </form>

                </td>

            </tr>
        </c:forEach>
    </table>
</div>                                
