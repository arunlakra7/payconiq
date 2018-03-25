import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { Stock } from 'app/models/stock.model';

@Injectable()
export class StocksService {

    constructor(private http:Http) {
    }

    getStocks(id?:string):Observable<Stock[]> {

        let url = "http://localhost:8080/api/stocks";
        return this.http.get(url)
            .map((res:Response) => res.json())
            .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
    }


}
