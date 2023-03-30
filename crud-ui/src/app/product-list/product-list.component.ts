import {Component, OnInit} from '@angular/core';
import {Product} from "../product";
import {ProductService} from "../product.service";
import {Router} from "@angular/router";
import {filter, map} from "rxjs";
import {compareSegments} from "@angular/compiler-cli/src/ngtsc/sourcemaps/src/segment_marker";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit{
  products: Product[] | undefined;
  keyword: String | undefined;
  order: Boolean = true;
  page: number=5;
  textReplace: String = "Filter" ;
  currentPage: any;
   totalPage: any;

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
  sort(field:String,order:Boolean){
    this.productService.sortProduct(field,order).subscribe(data =>{
      this.products = data;
    })
    this.order = !this.order;
    console.log(this.order);
  }
  pagination(pageNo:any,pageSize: number|undefined){
    this.productService.paginationProduct(pageNo,pageSize).subscribe(res =>{
      console.log(res);
      // @ts-ignore
      this.currentPage = res.pageable.pageNumber;
      // @ts-ignore
      this.totalPage = res.totalPages;
      if(pageNo<this.totalPage && pageNo>=0) {
        // @ts-ignore
        this.products = res.content;
      }
    });
  }

  quanLess(quan: number|undefined) {
      this.productService.quanLess(quan).subscribe(data =>{
      this.products = data;
    })
    this.textReplace = "Quantity Less than 10";
  }
  quanMore(quan: number|undefined) {
    this.productService.quanMore(quan).subscribe(data =>{
      this.products = data;
    })
    this.textReplace = "Quantity More than 10";
  }

  priceLess(price: number|undefined) {
    this.productService.priceLess(price).subscribe(data =>{
      this.products = data;
    })
    this.textReplace = "Price Less than 5000";

  }
  priceMore(price: number|undefined) {
    this.productService.priceMore(price).subscribe(data =>{
      this.products = data;
    })
    this.textReplace = "Price More than 5000";
  }

  reset() {
    this.textReplace = "Filter";
    this.getProducts();
  }

  resetSearch() {
    this.keyword = "";
    this.getProducts();
  }
}
