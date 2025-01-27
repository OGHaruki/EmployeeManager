import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

/**
 * Represents main navigation.
 */
@Component({
  selector: 'app-nav',
  standalone: true,
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css'],
  imports: [RouterModule]
})
export class NavComponent {

}
