import { Component, Input } from '@angular/core';
import { Article } from 'src/app/models/news';
import * as moment from 'moment';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.scss']
})
export class DisplayComponent {
  @Input() date: string = '';
  @Input() data: Article[] = [];
  getFormattedDate(date: any) {
    return moment(date).fromNow();
  }
}
