import { Component } from '@angular/core';
import { StocksService } from 'app/services/stocks.service';
import { Stock } from 'app/models/stock.model';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  stocks;

  constructor(private stocksService:StocksService) {
    this.stocks = this.stocksService.getStocks();
  }
}
