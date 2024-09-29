import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RecipeServiceService {

  private url = 'http://localhost:8080/api/v1/recipes';

  constructor(
    private http: HttpClient,
  ) { }

  public getRecipes(cuisine = 'world') {
    const url = `${this.url}?cuisineType=${cuisine}`;
    return this.http.get(url);
  }

  public getRecipesFromUrl(url: string) {
    const data = {
      url
    }
    return this.http.get(url);
  }

}
