import {Component, OnInit} from '@angular/core';
import {Product} from "../product";
import {ProductService} from "../product.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit{
  products: Product[] | undefined;
  keyword: String | undefined;

  constructor(private productService: ProductService,
              private router:Router) {
  }
  ngOnInit(): void {
    this.getProducts();
  }

  private getProducts() {
    this.productService.getProductList().subscribe(data => {
      this.products = data;
    })
  }
  updateProduct(id:number|undefined){
    this.router.navigate(['update',id]);
  }

  deleteProduct(id: number|undefined) {
    this.productService.deleteProduct(id).subscribe(res =>{
      console.log(res);
      this.getProducts();
    })
  }


  search(name: String|undefined) {
    this.productService.searchProduct(name).subscribe(data =>{
      this.products = data;

    })
  }
}
