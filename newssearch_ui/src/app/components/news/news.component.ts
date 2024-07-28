import { Component, Input } from '@angular/core';
import { Article } from 'src/app/models/news';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss'],
})
export class NewsComponent {
  @Input() news!: Article;

  navigate(url: string) {
    window.open(url, '_blank');
  }

  extractContent(content: string) {
    return content.substring(0, content.lastIndexOf('.'));
  }
}
