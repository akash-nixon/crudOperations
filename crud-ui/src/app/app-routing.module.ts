import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProductListComponent} from "./product-list/product-list.component";
import {CreateProductComponent} from "./create-product/create-product.component";
import {UpdateProductComponent} from "./update-product/update-product.component";

const routes: Routes = [
  {path:'products', component:ProductListComponent},
  {path:'create',component:CreateProductComponent},
  {path:'', redirectTo:'products', pathMatch:"full"},
  {path:'update/:id',component:UpdateProductComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
