import { Component, OnInit } from '@angular/core';
import { HttpServiceService } from './services/http-service.service';
import { NewsArticle } from './models/news';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  loading = false;
  form: FormGroup;
  newsArticle!: NewsArticle[];
  intervalOptions: string[] = ['minutes', 'hours', 'days', 'weeks', 'months', 'years'];
  constructor(
    private newsService: HttpServiceService,
    private fb: FormBuilder
  ) {
    this.form = this.fb.group({
      q: new FormControl('apple', [Validators.required]),
      u: new FormControl(),
      i: new FormControl(null, [Validators.min(0)]),
    });
  }
  ngOnInit(): void {
    this.getNews();
  }

  getNews() {
    const formValue = this.form.value;
    this.loading = true;
    this.newsService.getNews(formValue.q, formValue.u, formValue.i).subscribe({
      next: (data: NewsArticle[]) => {
        this.loading = false;
        this.newsArticle = data;
      },
      error: (err: any) => {
        this.loading = false;
        console.error('error while getting data ', err.error);
      },
    });
  }
}
