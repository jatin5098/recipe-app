import { Component, EventEmitter, Input, Output } from '@angular/core';
import { RecipeMetadata } from '../../../models/recipe-models';

@Component({
  selector: 'app-stat-panel',
  templateUrl: './stat-panel.component.html',
  styleUrl: './stat-panel.component.scss'
})
export class StatPanelComponent {

  @Input()
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

  @Output()
  public cuisineChange = new EventEmitter();

  @Output()
  public nextPageClick = new EventEmitter<string>();

  @Output()
  public prevPageClick = new EventEmitter<string>();

  public cuisineTypes = [
    '-- Select Cuisine --',
    'american',
    'asian',
    'british',
    'caribbean',
    'central europe',
    'chinese',
    'eastern europe',
    'french',
    'indian',
    'italian',
    'japanese',
    'korean',
    'mediterranean',
    'mexican',
    'middle eastern',
    'south american',
    'south east asian',
    'world',
  ];
  public cuisineType = 'world';

  public totalRecords = 0;
  public pageCount = 0;
  public currentPage = 0;
  public pageSize = 20;

  constructor() { }

  ngOnChanges() {
    if (this.recipesMetadata.count) {
      this.totalRecords = this.recipesMetadata.count;
      this.pageCount = Math.floor(this.recipesMetadata.count / this.pageSize);
      this.currentPage = Math.floor(this.recipesMetadata.from / this.pageSize) + 1;
    }
  }

  public onFilterChange(): void {
    this.cuisineChange.emit(this.cuisineType);
  }

  public fetchPrevPageItems(): void {
    this.prevPageClick.emit(this.recipesMetadata.linkPrev.href);
  }
  
  public fetchNextPageItems(): void {
    this.nextPageClick.emit(this.recipesMetadata.linkNext.href);
  }

}
