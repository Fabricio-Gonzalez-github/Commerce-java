# Commerce-java


## Entities (Client,Product,Invoice,InvoiceDetail)

### Relaciones Entre ENTIDADES

#### @ManyToOne PRODUCT/CLIENT

#### @ManyToMany CLIENT/PRODUCT

#### @OneToMany  CLIENT/INVOICE

#### @ManyToOne INVOICE/CLIENT

#### @OneToMany INVOICE/INVOICEDETAIL

#### @ManyToOne INVOICEDETAIL/INVOICE

## Repositorys (Client,Product,Invoice,InvoiceDetail)

###

## Services (Client,Product,Invoice,InvoiceDetail)

### TODOS LOS SERVICES

### SEVE / READALL / READONE / DELETE

#### services de Client: saveClient-readClients-readOneClient-destroyOneClient

#### services de Product: saveProduct-readProduct-readOneProduct-destroyOneProduct--(cart)--addToCart-removeFromCart-getClientCart

#### services de Invoice: saveInvoice-readInvoice-readOneInvoice-destroyInvoice 

#### services de InvoiceDetail: saveInvoiceDetail-readInvoiceDetail-readOneInvoiceDetail-destroyInvoiceDetail


## Controllers (Client,Product,Invoice,InvoiceDetail)

#### TODO LO MENCIONADO EN SERVICE + UPDATE de CLIENT Y PRODUCT


# URI


## CLIENT

#### SEVE : (localhost:8080/api/v1/clients)
#### READALL : (localhost:8080/api/v1/clients)
#### READONE : (localhost:8080/api/v1/clients/:id)
#### DELETE : (localhost:8080/api/v1/clients/:id)
#### UPDATE : (localhost:8080/api/v1/clients)


## PRODUCT

#### SEVE : (localhost:8080/api/v1/products)
#### READALL : (localhost:8080/api/v1/products)
#### READONE : (localhost:8080/api/v1/products/:id)
#### DELETE : (localhost:8080/api/v1/products/:id)
#### UPDATE : (localhost:8080/api/v1/products) 

### CART

#### addToCart : (localhost:8080/api/v1/products/:clientId/add/:productId)
#### removeFromCart : (localhost:8080/api/v1/products/:clientId/remove/:productId)
#### getClientCart : (localhost:8080/api/v1/products/clients/:clientId/cart)




## INVOICE

#### SEVE : (localhost:8080/api/v1/invoices)
#### READALL : (localhost:8080/api/v1/invoices)
#### READONE : (localhost:8080/api/v1/invoices/:id)
#### DELETE : (localhost:8080/api/v1/invoices/:id)


## INVOICE DETAIL

#### SEVE : (localhost:8080/api/v1/invoicesDetails)
#### READALL : (localhost:8080/api/v1/invoicesDetails)
#### READONE : (localhost:8080/api/v1/invoicesDetails/:id)
#### DELETE : (localhost:8080/api/v1/invoicesDetails/:id)
