import { Component, ViewEncapsulation } from '@angular/core';
import { RecipeServiceService } from '../../services/recipe-service.service';
import { RecipeMetadata } from '../../models/recipe-models';

@Component({
  selector: 'app-recipe-dashboard',
  templateUrl: './recipe-dashboard.component.html',
  styleUrls: ['./recipe-dashboard.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class RecipeDashboardComponent {

  public recipes: Array<Record<string, any>> = [];
  public recipesMetadata: RecipeMetadata = {
    count: 0,
    from: 0,
    to: 0,
    linkNext: {
      href: ''
    },
    linkPrev: {
      href: ''
    }
  };

  constructor(
    private recipeServiceService: RecipeServiceService,
  ) {

  }

  ngOnInit(): void {
    this.fetchRecipes('world');
  }

  public fetchRecipes(cuisine: string): void {
    this.recipeServiceService.getRecipes(cuisine)
      .subscribe((data: any) => {
        this.recipes = data.hits;

        // Metadata
        this.recipesMetadata = {
          count: data.count,
          from: data.from,
          to: data.to,
          linkNext: data._links.next,
          linkPrev: {
            href: ''
          }
        };
      });
  }

  public fetchRecipesFromUrl(url: string, isNextPage = true): void {
    this.recipeServiceService.getRecipesFromUrl(url)
      .subscribe((data: any) => {
        this.recipes = data.hits;
        const prevLink = isNextPage ? this.recipesMetadata.linkNext.href : this.recipesMetadata.linkPrev.href;

        // Metadata
        this.recipesMetadata = {
          count: data.count,
          from: data.from,
          to: data.to,
          linkNext: data._links.next,
          linkPrev: {
            href: prevLink
          }
        };
      });
  }

  public onCuisineSelectionChange(cuisine: string): void {
    this.fetchRecipes(cuisine);
  }

  public fetchPrevPageRecords(url: string): void {
    this.fetchRecipesFromUrl(url, false);
  }

  public fetchNextPageRecords(url: string): void {
    this.fetchRecipesFromUrl(url, true);
  }

}
