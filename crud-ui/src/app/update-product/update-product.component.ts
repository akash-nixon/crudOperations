import {Component, OnInit} from '@angular/core';
import {Product} from "../product";
import {ProductService} from "../product.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit{
  id: number|undefined;
  product: Product = new Product();
  constructor(private productService: ProductService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.productService.getProductById(this.id).subscribe(data => {
      this.product = data;
    }, error => console.log(error));
  }

  onSubmit(){
    this.productService.updateProduct(this.id, this.product).subscribe( data =>{
        this.goToProductsList();
      }
      , error => console.log(error));
  }

  goToProductsList(){
    this.router.navigate(['/products']);
  }
}
