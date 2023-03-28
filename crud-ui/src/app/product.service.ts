import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "./product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = "http://localhost:8080/products";
  constructor(private httpClient: HttpClient) { }

  getProductList(): Observable<Product[]>{
    return this.httpClient.get<Product[]>(`${this.baseUrl}`)
  }

  createProduct(product:Product): Observable<Object>{
    return this.httpClient.post(`http://localhost:8080/add`,product);
  }

  getProductById(id:number|undefined):Observable<Product>{
    return this.httpClient.get<Product>(`http://localhost:8080/productbyid/${id}`);
  }

  updateProduct(id: number|undefined, product: Product): Observable<Object>{
    return this.httpClient.put(`http://localhost:8080/update`, product);
  }

  deleteProduct(id:number|undefined):Observable<Object>{
    return this.httpClient.delete(`http://localhost:8080/delete/${id}`,{responseType:'text'});
  }

  searchProduct(name:String|undefined):Observable<Product[]>{
    return this.httpClient.get<Product[]>(`http://localhost:8080/search?name=${name}`);
  }
}
