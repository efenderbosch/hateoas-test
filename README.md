http://localhost:8080/childrenWithPagination
will return
```json
{
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/childrenWithPagination{?page,size,sort}",
      "templated" : true
    }
  },
  "_embedded" : {
    "children" : [ {
      "id" : 1,
      "attribute" : "attribute"
    } ]
  },
  "page" : {
    "size" : 20,
    "totalElements" : 1,
    "totalPages" : 1,
    "number" : 0
  }
}
```


http://localhost:8080/childrenWithoutPagination
will return
```json
[
  {
    "id":1,
    "attribute":"attribute",
    "parent":{
      "id":1,
      "attribute":"attribute"
    }
  }
]
```
