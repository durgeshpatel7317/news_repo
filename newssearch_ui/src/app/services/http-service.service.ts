import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SERVICE_URL } from 'src/environments/environment';
import { NewsArticle } from '../models/news';

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  constructor(private httpClient: HttpClient) { }

  getNews(q: string, u?: string, i?: string): Observable<NewsArticle[]>  {
    const url = `${SERVICE_URL}/api/v1/news`;
    const params: any = {q};
    if(u) {
      params.u = u;
    }
    if(i) {
      params.i = i;
    }
    return this.httpClient.get<NewsArticle[]>(url, {params: params});
  }
}
